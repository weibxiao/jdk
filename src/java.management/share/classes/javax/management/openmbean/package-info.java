/*
 * Copyright (c) 2001, 2024, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/**
 * <p>Provides the open data types and Open MBean descriptor classes.
 * An <em>Open MBean</em> is an MBean where the types of attributes
 * and of operation parameters and return values are built using a
 * small set of predefined Java classes.  Open MBeans facilitate
 * operation with remote management programs that do not necessarily
 * have access to application-specific types, including non-Java
 * programs.</p>
 *
 * <p>Every MBean has an {@link javax.management.MBeanInfo
 * MBeanInfo} with information about the MBean itself, and its
 * attributes, operations, constructors, and notifications.  In an
 * Open MBean, this <code>MBeanInfo</code> implements the {@link
 * javax.management.openmbean.OpenMBeanInfo OpenMBeanInfo}
 * interface, usually by being an instance of {@link
 * javax.management.openmbean.OpenMBeanInfoSupport
 * OpenMBeanInfoSupport}.</p>
 *
 * <p>The attribute information returned by {@link
 * javax.management.MBeanInfo#getAttributes()
 * MBeanInfo.getAttributes} for an Open MBean is an array of
 * objects implementing {@link
 * javax.management.openmbean.OpenMBeanAttributeInfo
 * OpenMBeanAttributeInfo}, usually instances of {@link
 * javax.management.openmbean.OpenMBeanAttributeInfoSupport
 * OpenMBeanAttributeInfoSupport}.  In addition to the usual
 * information about attributes, an
 * <code>OpenMBeanAttributeInfo</code> specifies the {@link
 * javax.management.openmbean.OpenType OpenType} of the attribute.
 * The possible <code>OpenType</code> values are predefined, which
 * is what ensures that remote managers will understand them.</p>
 *
 * <p>Similar remarks apply to the parameter types of operations and
 * constructors, and to the return types of operations.</p>
 *
 * <p>There is a distinction between an attribute's Java language
 * type, as returned by {@link
 * javax.management.MBeanAttributeInfo#getType() getType()}, and
 * its <code>OpenType</code>, as returned by {@link
 * javax.management.openmbean.OpenMBeanAttributeInfo#getOpenType()
 * getOpenType()}.  For example, if the Java language type is
 * <code>java.lang.String</code>, the <code>OpenType</code> will be
 * {@link javax.management.openmbean.SimpleType#STRING
 * SimpleType.String}.  If the Java language type is {@link
 * javax.management.openmbean.CompositeData}, the
 * <code>OpenType</code> will be a {@link
 * javax.management.openmbean.CompositeType CompositeType} that
 * describes the items in the <code>CompositeData</code> instances
 * for the attribute.</p>
 *
 * <h2><a id="constraints">Default values and constraints</a></h2>
 *
 * <p>In Open MBeans, attributes and parameters can have default values
 * and/or constraints associated with them in the {@code
 * OpenMBeanAttributeInfo} or {@code OpenMBeanParameterInfo}.
 * There are two ways to specify these constraints.  Either the
 * values are directly specified as parameters to one of the
 * constructors of {@code OpenMBeanAttributeInfoSupport} or
 * {@code OpenMBeanParameterInfoSupport}, for example
 * {@link
 * javax.management.openmbean.OpenMBeanParameterInfoSupport#OpenMBeanParameterInfoSupport(
 *String, String, OpenType, Object, Object[])}; or the values are
 * specified in a {@link javax.management.Descriptor Descriptor} given
 * as a parameter to one of the constructors.</p>
 *
 * <p>When a {@code Descriptor} is used, the fields of interest are
 * these:</p>
 *
 * <ul>
 *
 *   <li>{@code defaultValue} defines the value returned by
 * {@link javax.management.openmbean.OpenMBeanParameterInfo#getDefaultValue()
 * getDefaultValue()};
 *
 *   <li>{@code minValue} defines the value returned by {@link
 * javax.management.openmbean.OpenMBeanParameterInfo#getMinValue() getMinValue()};
 *
 *   <li>{@code maxValue} defines the value returned by {@link
 * javax.management.openmbean.OpenMBeanParameterInfo#getMaxValue() getMaxValue()};
 *
 *   <li>{@code legalValues} defines the values returned by {@link
 * javax.management.openmbean.OpenMBeanParameterInfo#getLegalValues() getLegalValues()}.
 *
 * </ul>
 *
 * <p>For {@code defaultValue}, {@code minValue}, and {@code
 *   maxValue}, the associated value must either be of the Java type
 *   corresponding to {@code openType}, or be a string that can be
 *   converted into that type.  The conversion uses the static method
 *   {@code valueOf(String)} if it finds one; otherwise a constructor
 *   with a single {@code String} parameter if it finds one; otherwise
 *   it fails.</p>
 *
 * <p>For {@code legalValues}, the associated value must be either
 *   an array or a {@code Set}, and the elements of the array or set
 *   must be convertible as described for {@code defaultValue} etc.</p>
 *
 * <p>The following conditions must be met for these fields:</p>
 *
 * <ul>
 *   <li>the values must be of the appropriate type, or be strings
 * that can be converted to the appropriate type as explained
 * above;
 *
 *   <li>if {@code legalValues} is present then neither {@code
 * minValue} nor {@code maxValue} must be present;
 *
 *   <li>if {@code defaultValue} is present then it must satisfy the
 * constraints defined by {@code legalValues}, {@code minValue}, or
 * {@code maxValue} when any of these is also present;
 *
 *   <li>if {@code minValue} and {@code maxValue} are both present
 * then {@code minValue} must not be greater than {@code maxValue}.
 * </ul>
 *
 * @see <a href="https://jcp.org/aboutJava/communityprocess/mrel/jsr160/index2.html">
 *   JMX Specification, version 1.4</a>
 *
 * @since 1.5
 */
package javax.management.openmbean;
